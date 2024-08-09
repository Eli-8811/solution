import axios from "axios";

const API_URL = "http://localhost:8090/demo-api/auth/";

class AuthService {

  async login(username, password) {
    const response = await axios
      .post(API_URL + "signin", {
        username,
        password
      });
    localStorage.setItem("user", JSON.stringify(response.data.data.user_auth));
    return response.data;
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }

}

export default new AuthService();
