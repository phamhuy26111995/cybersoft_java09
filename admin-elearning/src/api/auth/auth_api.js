import { API_CALL } from "../api_call";
import { API_URL } from "../../consts/path";

const authApi = {
    login,
}

function login(body) {
   return API_CALL.nonAuth(API_URL.AUTH.LOGIN, body)
}

export default authApi;