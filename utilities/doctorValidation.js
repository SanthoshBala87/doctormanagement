const MIN_AGE = 22;
const MAX_AGE = 60;

export const doctorName = document.querySelector("#doctor-name");
export const doctorEmail = document.querySelector("#doctor-email");
export const doctorAge = document.querySelector("#doctor-age");
export const doctorPhoneNumber = document.querySelector("#doctor-phone-number");

const doctorNameError = document.querySelector(".doctor-name-error");
const doctorEmailError = document.querySelector(".doctor-email-error");
const doctorAgeError = document.querySelector(".doctor-age-error");
const doctorPhoneNumberError = document.querySelector(
  ".doctor-phone-number-error",
);

export const errorDataSection = document.querySelector(".error-data");
const timestamp = document.querySelector("#timestamp");
const errorStatus = document.querySelector("#status");
const errorCode = document.querySelector("#error-code");

export function validateDetails() {
  const errors = {};
  validateName(errors);
  validateEmail(errors);
  validateAge(errors);
  validatePhoneNumber(errors);
  if (Object.keys(errors).length > 0) {
    setErrors(errors);
    return false;
  }
  setErrors(errors);
  return true;
}

function validateName(errors) {
  const name = doctorName.value.trim();
  if (name === "" || !/^[A-Za-z\.\s]{3,30}$/.test(name)) {
    errors.doctorName = "Please enter a valid name !";
  }
}

function validateEmail(errors) {
  const email = doctorEmail.value.trim();
  if (
    email === "" ||
    !/^[A-Za-z][A-Za-z0-9\.\,\_\-]*@[A-Za-z]{2,15}\.[A-Za-z]{2,15}$/.test(email)
  ) {
    errors.doctorEmail = "Please enter a valid email !";
  }
}

function validateAge(errors) {
  const age = Number(doctorAge.value.trim());
  if (Number.isNaN(age) || age < MIN_AGE || age > MAX_AGE) {
    errors.doctorAge = "Please enter a avlid age !";
  }
}

function validatePhoneNumber(errors) {
  const phoneNumber = doctorPhoneNumber.value.trim();
  if (phoneNumber === "" || !/^[6-9][0-9]{9}$/.test(phoneNumber)) {
    errors.doctorPhoneNumber = "Please enter a valid phone number !";
  }
}

export function setErrors(errors) {
  setNameError(errors);
  setEmailError(errors);
  setAgeError(errors);
  setPhoneNumberError(errors);
}

function setNameError(errors) {
  if (errors.doctorName) {
    doctorNameError.textContent = errors.doctorName;
    displayBlock(doctorNameError);
  } else {
    doctorNameError.textContent = "";
    displayNone(doctorNameError);
  }
}

function setEmailError(errors) {
  if (errors.doctorEmail) {
    doctorEmailError.textContent = errors.doctorEmail;
    displayBlock(doctorEmailError);
  } else {
    doctorEmailError.textContent = "";
    displayNone(doctorEmailError);
  }
}

function setAgeError(errors) {
  if (errors.doctorAge) {
    doctorAgeError.textContent = errors.doctorAge;
    displayBlock(doctorAgeError);
  } else {
    doctorAgeError.textContent = "";
    displayNone(doctorAgeError);
  }
}

function setPhoneNumberError(errors) {
  if (errors.doctorPhoneNumber) {
    doctorPhoneNumberError.textContent = errors.doctorPhoneNumber;
    displayBlock(doctorPhoneNumberError);
  } else {
    doctorPhoneNumberError.textContent = "";
    displayNone(doctorPhoneNumberError);
  }
}

export function assignErrors(error) {
  displayBlock(errorDataSection);
  timestamp.textContent = error.timestamp;
  errorStatus.textContent = error.status;
  errorCode.textContent = error.errorCode;
  const exceptions = error.exceptions;
  if (exceptions.doctorName) {
    setNameError(exceptions);
  }
  if (exceptions.doctorEmail) {
    setEmailError(exceptions);
  }
  if (exceptions.doctorAge) {
    setAgeError(exceptions);
  }
  if (exceptions.doctorPhoneNumber) {
    setPhoneNumberError(exceptions);
  }
}

export function displayBlock(variable) {
  variable.classList.remove("display-none");
  variable.classList.add("display-block");
}

export function displayNone(variable) {
  variable.classList.add("display-none");
  variable.classList.remove("display-block");
}

export function displayFlex(variable) {
  variable.classList.add("display-flex");
  variable.classList.remove("display-none");
}
