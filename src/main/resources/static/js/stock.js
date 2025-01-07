function connect() {
	ws = new WebSocket("ws://172.16.105.174:10120");
	ws.onmessage = onMessage;
}
function onMessage(evt) {
	const data = evt.data;
	updateChart(data);
}

let chart;
let seriesData = []; // 전역 변수로 정의된 seriesData
let currentCandlestick = null; // 현재 봉 데이터
let prevClose = 0; // 이전 봉 종가
let startTime = null; // 봉 수정 시작 시간
let currentDate = new Date(); // 첫 봉의 날짜를 추적할 currentDate 변수

// 페이지 로드 시 차트 그리기
$(function() {
	getGraph();
	connect();
	drawChart();
});

function getGraph(){
	$.ajax({
		url : "/stock/graphUpdate",
		dataType : 'json',
		success : function(result){
			console.log(result);
			$("#todayDate").html(Intl.DateTimeFormat('en-CA', {timeZone: 'UTC'}).format(new Date(result.tradeDate)));
			$("#todayEndPrice").html(new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(result.endPrice));
			if(result.rateOfChange > 0){
				$("#todateRateOfChange").html('+'+result.rateOfChange+'%');
				$("#todateRateOfChange").css("color", "red");
			}
			if(result.rateOfChange < 0){
				$("#todateRateOfChange").html(result.rateOfChange+'%');
				$("#todateRateOfChange").css("color", "blue");
			}
			$("#todayTotalVolume").html(result.totalVolume.toLocaleString());
			$("#todayTotalPrice").html(new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(result.totalPrice));
		},
		error : function(){
			alert("에러가 발생했습니다.");
		}
	})
}

function drawChart() {
  function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}.${month}.${day}`;
  }

  // 초기 차트 데이터
  const initialData = stockList.map((stock) => ({
    x: formatDate(stock.tradeDate), // tradeDate를 YYYY.MM.DD 형식으로 변환
    y: [stock.openPrice, stock.maxPrice, stock.minPrice, stock.endPrice]
  }));

  seriesData = [...initialData]; // 기존 데이터 저장

  const options = {
    chart: {
      type: 'candlestick',
      height: 350,
      animations: {
        enabled: false,
      },
      zoom: {
        enabled: false,
      },
    },
    series: [
      {
        data: seriesData, // 초기 데이터 설정
      }
    ],
    xaxis: {
      type: 'category', // 'category'로 변경하여 날짜 비교가 제대로 되게 함
      labels: {
        show: true,
        formatter: function(value) {
          // 'value'는 이미 YYYY.MM.DD 형식으로 전달됨
          return value; 
        },
      },
    },
	tooltip: {
	    enabled: true,
	    followCursor: true,  // 마우스를 따라다니게 설정
	    shared: true,        // 여러 데이터가 한 번에 툴팁에 나타나도록 설정
	    fixed: {
	      enabled: false,    // 툴팁 고정 안함
	    }
	  },
    plotOptions: {
      candlestick: {
        colors: {
          upward: '#DF7D46',
          downward: '#3C90EB',
        },
      },
    },
  };

  chart = new ApexCharts(document.querySelector("#chart"), options);
  chart.render(); // 차트 렌더링
}


function updateChart(data) {
  const parsedData = JSON.parse(data);
  const now = new Date();
  var isChange = false;  // 변경 여부를 추적하는 변수

  // 오늘 날짜를 "2025.01.06" 형식으로 생성
  const todayDate = `${now.getFullYear()}.${String(now.getMonth() + 1).padStart(2, '0')}.${String(now.getDate()).padStart(2, '0')}`;

  // 오늘 날짜의 차트를 찾기
  let todayCandlestick = seriesData.find(item => item.x === todayDate);

  if (!todayCandlestick) {
    // 오늘 날짜의 봉이 없을 경우 새로 생성
    todayCandlestick = {
      x: todayDate,
      y: [parsedData.price, parsedData.price, parsedData.price, parsedData.price], // open, high, low, close
    };
    seriesData.push(todayCandlestick); // 새로운 봉 추가
    isChange = true;
  } else {
    // 오늘 날짜의 봉이 이미 존재하면 수정
    const prevHigh = todayCandlestick.y[1];
    const prevLow = todayCandlestick.y[2];
    const prevClose = todayCandlestick.y[3];

    // 고가, 저가, 종가 업데이트
    todayCandlestick.y[1] = Math.max(todayCandlestick.y[1], parsedData.price); // high 갱신
    todayCandlestick.y[2] = Math.min(todayCandlestick.y[2], parsedData.price); // low 갱신
    todayCandlestick.y[3] = parsedData.price; // close 갱신

    // 데이터가 변경되었으면 isChange를 true로 설정
    if (todayCandlestick.y[1] !== prevHigh || todayCandlestick.y[2] !== prevLow || todayCandlestick.y[3] !== prevClose) {
      isChange = true;
    }
  }

  // 차트 데이터가 변경되었으면 업데이트
  if (isChange) {
	getGraph();	
    chart.updateSeries([{
      data: seriesData, // 업데이트된 데이터만 전달
    }], false); // 'false' 플래그로 애니메이션 유지
  }
}

function padZero(num) {
  return num < 10 ? '0' + num : num;
}
