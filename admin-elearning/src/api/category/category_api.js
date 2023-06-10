import { API_CALL } from "../api_call";
import { API_URL } from "../../consts/path";

const categoryApi = {
  getAll,
  getById,
  save,
  update
};

function getAll() {
  return API_CALL.get(API_URL.CATEGORIES.GET_ALL, {});
}

function getById(body) {
  return API_CALL.get(API_URL.CATEGORIES.GET_BY_ID, body);
}

function save(body) {
  return API_CALL.postFormData(API_URL.CATEGORIES.SAVE, body);
}
function update(body) {
  return API_CALL.putFormData(API_URL.CATEGORIES.UPDATE, body);
}

export default categoryApi;
