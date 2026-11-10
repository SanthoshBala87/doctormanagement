const receivedDoctorId = document.querySelector("#received-doctor-id");
const receivedDoctorName = document.querySelector("#received-doctor-name");
const receivedDoctorEmail = document.querySelector("#received-doctor-email");
const receivedDoctorAge = document.querySelector("#received-doctor-age");
const receivedDoctorPhoneNumber = document.querySelector(
  "#received-doctor-phone-number",
);

const deleteDoctor = document.querySelector("#delete-doctor");
const successMessage = document.querySelector(".success-message");

const addDataSection = document.querySelector(".add-data");

const doctorId = document.querySelector("#doctor-id");
const doctorIdError = document.querySelector(".doctor-id-error");

const getDetails = document.querySelector("#get-doctor-details");
getDetails.addEventListener("click", getId);
function getId() {
  displayNone(successMessage);
  displayNone(addDataSection);
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
    displayBlock(doctorIdError);
  } else {
    doctorIdError.textContent = "";
    displayNone(doctorIdError);
  }
}

async function getDoctorData() {
  const id = doctorId.value.trim().split("DID")[1];
  const response = await fetch(
    `https://doctor-management-api-7u18.onrender.com/api/doctor/${id}`,
  );
  if (!response.ok) {
    const error = await response.json();
    displayNone(addDataSection);
    displayBlock(doctorIdError);
    doctorIdError.textContent = error.exceptions.exception;
    return;
  }
  displayNone(doctorIdError);
  doctorIdError.textContent = "";
  const data = await response.json();
  setDoctorData(data);
}

function setDoctorData(data) {
  displayBlock(addDataSection);
  receivedDoctorId.textContent = data.doctorId;
  receivedDoctorName.textContent = data.doctorName;
  receivedDoctorEmail.textContent = data.doctorEmail;
  receivedDoctorAge.textContent = data.doctorAge;
  receivedDoctorPhoneNumber.textContent = data.doctorPhoneNumber;
}

deleteDoctor.addEventListener("click", deleteDoctorDetails);
async function deleteDoctorDetails() {
  deleteDoctor.disabled = true;
  displayNone(addDataSection);
  try {
    const id = doctorId.value.trim().split("DID")[1];
    const response = await fetch(
      `https://doctor-management-api-7u18.onrender.com/api/doctor/${id}`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      },
    );
    if (!response.ok) {
      return;
    }
    const data = await response.text();
    displayBlock(successMessage);
    showData(successMessage);
    deleteDoctor.disabled = false;
  } catch (exception) {
    console.log(exception.message);
  }
}

let id;
function showData(variable) {
  clearTimeout(id);
  id = setTimeout(() => {
    displayNone(variable);
  }, 5000);
}

function displayBlock(variable) {
  variable.classList.add("display-block");
  variable.classList.remove("display-none");
}

function displayNone(variable) {
  variable.classList.add("display-none");
  variable.classList.remove("display-block");
}
