/**
 * 
 */
function count(type)  {
  // 결과를 표시할 element
  resultElement = document.getElementById('quantity');

  // 현재 화면에 표시된 값
  var number = resultElement.value;
  
  // 더하기/빼기
  if(type === 'plus') {
    number = parseInt(number) + 1;
  }else if(type === 'minus')  {
    number = parseInt(number) - 1;
    if(number<1){
    	number=1;
    }
  }
  // 결과 출력 quantity
  resultElement.value = number;
}