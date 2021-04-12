export class User {
  id: string;
  name: string;
  surname: string;
  email: string;
  userRole: string;
  password: string;

  constructor(id?: string, name?: string, surname?: string, email?: string, userRole?: string, password?: string) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.userRole = userRole;
    this.password = password;
  }
}
