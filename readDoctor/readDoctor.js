const API_URL = "http://localhost:8080/api/doctor";

const message = document.querySelector(".message");
const errorMessage = document.querySelector(".error-message");

const showData = document.querySelector(".show-doctor-data");
const template = document.querySelector(".doctor-template");

const readButton = document.querySelector("#read-all-doctors");

readButton.addEventListener("click", readAllDoctors);
async function readAllDoctors() {
  try {
    const response = await fetch(API_URL);
    if (!response.ok) {
      const error = await response.json();
      displayFlex(message);
      errorMessage.textContent = error.exceptions.exception;
      return;
    }
    displayFlexNone(message);
    const data = await response.json();
    console.log(data);
    displayData(data);
  } catch (error) {
    console.log(error.message);
  }
}

function displayData(data) {
  const doctorDatas = document.querySelectorAll(".doctor-data");
  if (doctorDatas.length > 0) {
    showData.removeChild();
  }
  displayBlock(showData);
  for (let doctorData in data) {
    const clone = template.content.cloneNode(true);
    clone.querySelector(".doctor-id").textContent = data[doctorData].doctorId;
    clone.querySelector(".doctor-name").textContent =
      data[doctorData].doctorName;
    clone.querySelector(".doctor-email").textContent =
      data[doctorData].doctorEmail;
    clone.querySelector(".doctor-age").textContent = data[doctorData].doctorAge;
    clone.querySelector(".doctor-phone-number").textContent =
      data[doctorData].doctorPhoneNumber;
    showData.appendChild(clone);
  }
}

function displayFlex(variable) {
  variable.classList.add("display-flex");
  variable.classList.remove("display-none");
}

function displayFlexNone(variable) {
  variable.classList.add("display-none");
  variable.classList.remove("display-flex");
}

function displayBlock(variable) {
  variable.classList.add("display-block");
  variable.classList.remove("display-none");
}
