const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
const movieSelect = document.getElementById('movie');
const seatList="";
let ticketPrice = +movieSelect.value;

//Update total and count
function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll('.row .seat.selected');
  const selectedSeatsCount = selectedSeats.length;
  count.innerText = selectedSeatsCount;
  total.innerText = selectedSeatsCount * ticketPrice;
}

//Movie Select Event
movieSelect.addEventListener('change', e => {
  ticketPrice = +e.target.value;
  updateSelectedCount();
});

//Seat click event
container.addEventListener('click', e => {
  if (e.target.classList.contains('seat') &&
     !e.target.classList.contains('occupied')) {
    e.target.classList.toggle('selected');
    seatList=e.target.getAttribute('value')+",";
  }
  updateSelectedCount();
});

function getSeats(){
let selectedSeats="";
let selectedSeatElement = document.getElementByClass('selected');
for(let i=0;i<selectedSeatElement.length;i++){
selectedSeats=selectedSeatElement.getAttribute('value')+","
}
document.getElementById('seats').setAttribute("th:field",selectedSeats);
}