/**
 * 
 */
function connect() {
	ws = new WebSocket("ws://172.16.105.174:10120");
	ws.onmessage = onMessage;
}
function appendMessage(msg){
	$("#chatMessageArea").append(msg + "<br />");
	var chatAreaHeight = $("#chatArea").height();
	var maxScroll = $("#chatMessageArea").height() - chatAreaHeight
	$("#chatArea").scrollTop(maxScroll);
}
function onMessage(evt) {
	//const data = evt.data;
	var data = '{"price" : '+(Math.random() * 1000 + 50000).toFixed(0)+'}';
	appendMessage(data);
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
  drawChart();
  // 일정 주기로 updateChart 호출 (예: 1초마다)
  setInterval(() => {
    onMessage(); // 메시지를 받아오는 함수 호출
  }, 100); // 1초마다
});

function drawChart() {
  // 초기 차트 데이터
  const initialData = stockList.map((stock) => ({
    x: stock.tradeDate, // 타임스탬프가 제대로 되어있다면 이 값을 그대로 사용
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
      type: 'datetime', // x축을 datetime 타입으로 설정
      labels: {
        show: true,
        formatter: function(value) {
          const newDate = new Date(value);
          const year = padZero(newDate.getFullYear());
          const month = padZero(newDate.getMonth() + 1);
          const date = padZero(newDate.getDate());
          return `${year}.${month}.${date}`;
        },
      },
    },
	tooltip: {
	    enabled: true, // 툴팁 활성화
	    shared: true, // 여러 시리즈에 대해 공통 툴팁 표시
	    followCursor: false, // 마우스를 따라다니지 않도록 설정
	    theme: 'dark', // 툴팁의 테마 설정 (선택 사항)
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
  
  // 봉이 처음 생성될 때
  if (!startTime) {
    startTime = now.getTime(); // 첫 번째 봉 수정 시작 시간 기록
    currentCandlestick = {
      x: now.getTime(), // 첫 봉의 타임스탬프
      y: [parsedData.price, parsedData.price, parsedData.price, parsedData.price], // 첫 봉의 open, high, low, close
    };
  }

  // 5초마다 새로운 봉을 생성
  if (now.getTime() - startTime >= 5000) {
    // 기존 봉을 seriesData에 추가
    seriesData.push(currentCandlestick);
    
    // 새로운 봉을 시작 (날짜 하루 증가)
    currentDate.setDate(currentDate.getDate() + 1); // 날짜 하루 증가
    const newDate = new Date(currentDate); // 새로운 날짜를 타임스탬프로 변환

    startTime = now.getTime(); // 시작 시간 갱신
    currentCandlestick = {
      x: newDate.getTime(), // 새로운 날짜의 타임스탬프
      y: [parsedData.price, parsedData.price, parsedData.price, parsedData.price], // 새로운 봉의 open, high, low, close
    };
  } else {
    // 5초 동안은 봉을 수정
    if (currentCandlestick && currentCandlestick.y) {
      // high, low, close 갱신
      if (currentCandlestick.y[1] < parsedData.price) {
        currentCandlestick.y[1] = parsedData.price; // high 갱신
      }
      if (currentCandlestick.y[2] > parsedData.price) {
        currentCandlestick.y[2] = parsedData.price; // low 갱신
      }
      currentCandlestick.y[3] = parsedData.price; // close 갱신
    } else {
      console.error("currentCandlestick or currentCandlestick.y is invalid");
    }
  }

  // 차트 데이터 업데이트: 기존 데이터와 새로운 봉 모두 유지
  chart.updateSeries([{
    data: [...seriesData, currentCandlestick], // 기존 데이터와 새로운 데이터 모두 유지
  }], false); // 'false' 플래그는 애니메이션을 비활성화하지 않음
}

function padZero(num) {
  return num < 10 ? '0' + num : num;
}
