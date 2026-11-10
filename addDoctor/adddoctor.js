import * as doctorValidation from "../utilities/doctorValidation.js";

const API_URL = "http://localhost:8080/api/doctor";

const receivedDoctorId = document.querySelector("#received-doctor-id");
const receivedDoctorName = document.querySelector("#received-doctor-name");
const receivedDoctorEmail = document.querySelector("#received-doctor-email");
const receivedDoctorAge = document.querySelector("#received-doctor-age");
const receivedDoctorPhoneNumber = document.querySelector(
  "#received-doctor-phone-number",
);

const resetButton = document.querySelector("#reset-button");
const submitButton = document.querySelector("#submit-button");

const addDataSection = document.querySelector(".add-data");

resetButton.addEventListener("click", resetDetails);
function resetDetails() {
  doctorValidation.doctorName.value = "";
  doctorValidation.doctorEmail.value = "";
  doctorValidation.doctorAge.value = "";
  doctorValidation.doctorPhoneNumber.value = "";
  doctorValidation.doctorName.focus();
}

submitButton.addEventListener("click", submitDetails);
function submitDetails() {
  submitButton.disabled = true;
  if (!doctorValidation.validateDetails()) {
    submitButton.disabled = false;
    return;
  }
  addDoctorDetails();
  submitButton.disabled = false;
}

// Adding doctors details
async function addDoctorDetails() {
  doctorValidation.displayNone(doctorValidation.errorDataSection);
  doctorValidation.displayNone(addDataSection);
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(doctorData()),
    });
    if (!response.ok) {
      const error = await response.json();
      doctorValidation.assignErrors(error);
      showData(doctorValidation.errorDataSection);
      return;
    }
    const data = await response.json();
    postData(data);
    showData(addDataSection);
  } catch (exception) {
    // console.log(exception.message);
  }
}

function doctorData() {
  const doctorDetails = {
    doctorName: doctorValidation.doctorName.value.trim(),
    doctorEmail: doctorValidation.doctorEmail.value.trim(),
    doctorAge: doctorValidation.doctorAge.value.trim(),
    doctorPhoneNumber: doctorValidation.doctorPhoneNumber.value.trim(),
  };
  return doctorDetails;
}

function postData(data) {
  doctorValidation.displayBlock(addDataSection);
  receivedDoctorId.textContent = data.doctorId;
  receivedDoctorName.textContent = data.doctorName;
  receivedDoctorEmail.textContent = data.doctorEmail;
  receivedDoctorAge.textContent = data.doctorAge;
  receivedDoctorPhoneNumber.textContent = data.doctorPhoneNumber;
}
let id;
function showData(variable) {
  clearTimeout(id);
  id = setTimeout(() => {
    doctorValidation.displayNone(variable);
    const errorMessages = document.querySelectorAll(".errors");
    for (let li of errorMessages) {
      li.remove();
    }
  }, 5000);
}
