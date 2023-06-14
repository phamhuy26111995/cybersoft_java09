import { API_CALL } from "../api_call";
import { API_URL } from "../../consts/path";

const userApi = {
  getCurrent,
  search,
  init,
  save,
  getDetail
};

function getCurrent() {
  return API_CALL.get(API_URL.USER.GET_CURRENT, {});
}

function search(body) {
  return API_CALL.post(API_URL.USER.SEARCH, body);
}

function init() {
  return API_CALL.get(API_URL.USER.INIT, {});
}

function save(body) {
    return API_CALL.postFormData(API_URL.USER.SAVE, body);
  }

  function getDetail(body) {
    return API_CALL.post(API_URL.USER.GET_DETAIL, body);
  }

export default userApi;
