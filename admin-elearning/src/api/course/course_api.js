import { API_CALL } from "../api_call";
import { API_URL } from "../../consts/path";

const courseApi = {
  getAll,
  getByCondition,
  getDetail,
  save,
  saveCourseContent,
  update,
  updateContent
};

function getAll(body) {
  return API_CALL.post(API_URL.COURSES.GET_ALL, body);
}

function getByCondition(body) {
  return API_CALL.post(API_URL.COURSES.GET_BY_CONDITION, body);
}

function getDetail(body) {
  return API_CALL.post(API_URL.COURSES.GET_DETAIL, body);
}

function save(body) {
  return API_CALL.postFormData(API_URL.COURSES.SAVE, body);
}

function update(body) {
  return API_CALL.putFormData(API_URL.COURSES.UPDATE, body);
}

function updateContent(body) {
  return API_CALL.put(API_URL.COURSES.UPDATE_CONTENT, body);
}


function saveCourseContent(body) {
  return API_CALL.post(API_URL.COURSES.SAVE_CONTENT, body);
}





export default courseApi;
