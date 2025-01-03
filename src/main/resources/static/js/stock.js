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
	const data = evt.data;
	appendMessage(data);
	updateChart(data)
}

$(function() {
  connect();
  drawChart();
});

let chart;
let seriesData = []; // seriesData를 전역 변수로 정의
let currentCandlestick = null; // 현재 봉 데이터
let prevClose = 0; // 이전 봉 종가

function drawChart() {
	const seriesData = stockList.map((stock, index) => ({
	    x: stock.tradeDate,
	    y: [stock.openPrice, stock.maxPrice, stock.minPrice, stock.endPrice]
	}));
	console.log(seriesData);
	const options = {
	  chart: {
	    type: 'candlestick',
	    height: 350,
	    animations: {
	      enabled: false,  // 차트와 툴팁의 모든 애니메이션 비활성화
	    },
	    zoom: {
	      enabled: false, // 확대/축소 비활성화
	    },
	  },
	  series: [
	    {
			"data": seriesData
	    }
	  ],
	  xaxis: {
	    type: '', // x축을 datetime 타입으로 설정
	    labels: {
	      show: true, // x축 레이블을 표시
	      formatter: function(value) {
	        const date = new Date(value); // value는 밀리초 단위로 변환된 타임스탬프
	        const hours = padZero(date.getFullYear());
	        const minutes = padZero(date.getMonth() + 1);
	        const seconds = padZero(date.getDate());
	        return `${hours}.${minutes}.${seconds}`; 
	      },
	    },
	  },
	  tooltip: {
	    enabled: true, // 툴팁 비활성화
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

function updateChart(){
	const now = new Date();
	const seconds = now.getSeconds();
	const minutes = now.getMinutes();
	const hours = now.getHours();
	const day = now.getDate();
	const month = now.getMonth() + 1;
	const year = now.getFullYear();
  	const parsedData = JSON.parse(data);
  	const currentTime = `${year}.${padZero(month)}.${padZero(day)} ${padZero(hours)}:${padZero(minutes)}:${padZero(seconds)}`;
	if (hours == 0) {
	  if (currentCandlestick) {
	    seriesData.push(currentCandlestick); // 기존 봉 추가
	  }

	  // prevClose가 0인 경우 초기값을 설정
	  if (prevClose === 0) {
	    prevClose = parsedData.price;
	  }

	  currentCandlestick = {
	    x: new Date(currentTime).getTime(), // x는 밀리초 단위로 변환한 시간
	    y: [prevClose, parsedData.price, parsedData.price, parsedData.price], // open, high, low, close
	  };
	} 
	else {
	  if (currentCandlestick.y[1] < parsedData.price) {
	    currentCandlestick.y[1] = parsedData.price; // high 갱신
	  }
	  if (currentCandlestick.y[2] > parsedData.price) {
	    currentCandlestick.y[2] = parsedData.price; // low 갱신
	  }
	  currentCandlestick.y[3] = parsedData.price; // close 갱신
	  prevClose = currentCandlestick.y[3];
	  
	  chart.updateSeries([
	  	  {
	  	    data: seriesData,
	  	  },
	  	]);
	}
}
// 숫자가 10보다 작으면 앞에 0을 추가하는 함수
function padZero(value) {
  return value < 10 ? "0" + value : value;
}