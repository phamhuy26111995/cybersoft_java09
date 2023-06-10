const prefix = "api/v1/admin";
const api_url = "http://localhost:8080";

export const PAGE_URL = {
  ALL: "*",
  HOME: "/",
  TEST: "/test",
  COURSES: {
    INDEX: "/courses",
    DETAIL: "/courses/:id",
  },
  LOGIN: "/login",
  CATEGORIES: {
    INDEX: "/categories",
    DETAIL: "/categories/:id",
  },
  USERS: {
    INDEX: "/users",
    DETAIL: "/users/:id",
  },
};

export const API_URL = {
  AUTH: {
    LOGIN: `${api_url}/${prefix}/auth/login`,
  },

  CATEGORIES: {
    GET_ALL: `${api_url}/${prefix}/categories/get-all`,
    GET_BY_ID: `${api_url}/${prefix}/categories/:id`,
    SAVE: `${api_url}/${prefix}/categories/save`,
    UPDATE: `${api_url}/${prefix}/categories/edit`,
  },

  COURSES: {
    GET_ALL: `${api_url}/${prefix}/courses/search-all`,
    GET_BY_CONDITION: `${api_url}/${prefix}/courses/search`,
    GET_DETAIL: `${api_url}/${prefix}/courses/detail`,
    SAVE: `${api_url}/${prefix}/courses/save`,
    SAVE_CONTENT: `${api_url}/${prefix}/courses/save-content`,
    UPDATE: `${api_url}/${prefix}/courses/edit`,
    UPDATE_CONTENT: `${api_url}/${prefix}/courses/edit-content`,
  },

  USER: {
    FIND_BY_ID: `${api_url}/${prefix}/users/find-by-id`,
    GET_CURRENT: `${api_url}/${prefix}/users/get-current-user`,
  },
};
