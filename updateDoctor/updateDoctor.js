import * as doctorValidation from "../utilities/doctorValidation.js";

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

const doctorId = document.querySelector("#doctor-id");
const doctorIdError = document.querySelector(".doctor-id-error");

const getDetails = document.querySelector("#get-doctor-details");
getDetails.addEventListener("click", getId);
function getId() {
  getDetails.disabled = true;
  const errors = {};
  if (validateId(errors)) {
    setIdError(errors);
  }
  setIdError(errors);
  getDoctorData();
  getDetails.disabled = false;
}

function validateId(errors) {
  const id = doctorId.value.trim();
  if (id === "" || !/^DID[0-9]{1,}$/.test(id)) {
    errors.doctorId = "Please enter a valid id !";
    return true;
  }
  return false;
}
function setIdError(errors) {
  if (errors.doctorId) {
    doctorIdError.textContent = errors.doctorId;
    doctorValidation.displayBlock(doctorIdError);
  } else {
    doctorIdError.textContent = "";
    doctorValidation.displayNone(doctorIdError);
  }
}

async function getDoctorData(){
    const id = doctorId.value.trim().split("DID")[1];
    const response=await fetch(`http://localhost:8080/api/doctor/${id}`);
    if(!response.ok){
        const error=await response.json();
        doctorValidation.displayBlock(doctorIdError);
        doctorIdError.textContent=error.exceptions.exception;
        console.log(error);
        return;
    }
    doctorValidation.displayNone(doctorIdError);
    doctorIdError.textContent ="";
    const data=await response.json();
    setDoctorData(data);
}

function setDoctorData(data) {
  doctorValidation.doctorName.value = data.doctorName;
  doctorValidation.doctorEmail.value = data.doctorEmail;
  doctorValidation.doctorAge.value = data.doctorAge;
  doctorValidation.doctorPhoneNumber.value = data.doctorPhoneNumber;
  doctorValidation.doctorName.focus();
}

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
  editDoctorDetails();
  submitButton.disabled = false;
}

async function editDoctorDetails() {
  doctorValidation.displayNone(doctorValidation.errorDataSection);
  doctorValidation.displayNone(addDataSection);
  try {
    const id = doctorId.value.trim().split("DID")[1];
    const response = await fetch(`http://localhost:8080/api/doctor/${id}`, {
      method: "PUT",
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
    console.log(exception.message);
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
