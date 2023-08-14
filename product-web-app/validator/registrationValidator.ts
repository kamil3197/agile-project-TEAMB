import User from '../model/register.js';

export default function registerValidator(input: User) {
  const { email, password, role } = input;
  const minLength: number = 8;

  if (!(role === 'Admin' || role === 'Employee')) {
    throw Error('You must choose your role from the form.');
  }

  if (password.length < minLength) {
    throw Error(`Password must be at least ${minLength} characters long.`);
  }

  if (/\s/.test(email)) {
    throw Error('Email cannot contain whitespace.');
  }

  if (!/(?=.*[a-z])/.test(password)) {
    throw Error('Password must contain at least one lowercase letter.');
  }

  if (!/(?=.*[A-Z])/.test(password)) {
    throw Error('Password must contain at least one uppercase letter.');
  }

  if (!/(?=.*[!@#$%^&*(),.?":{}|<>])/.test(password)) {
    throw Error('Password must contain at least one special character.');
  }

  if (/\s/.test(password)) {
    throw Error('Password cannot contain whitespace.');
  }

  return null;
}
