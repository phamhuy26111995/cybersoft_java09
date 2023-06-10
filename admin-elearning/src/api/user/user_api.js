import { API_CALL } from "../api_call";
import { API_URL } from "../../consts/path";

const userApi = {
    getCurrent,
}


function getCurrent() {
   return API_CALL.get(API_URL.USER.GET_CURRENT , {});
}

export default userApi;