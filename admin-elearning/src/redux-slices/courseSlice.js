import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import courseApi from "../api/course/course_api";
import {
  showLoading,
  hideLoading,
  showError,
  showSuccess,
} from "../redux-slices/globalSlice";
import { PAGE_URL } from "../consts/path";

const initialState = {
  courses: [],
  courseDetail: undefined,
  total : 0
};

export const fetchAllCourse = createAsyncThunk(
  "course/fetchAllCourse",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
      const response = await courseApi.getAll(body);
      thunkAPI.dispatch(hideLoading());
      return response;
    } catch (err) {
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Không thể lấy được thông tin các khóa học"));
    }
  }
);

export const getDetail = createAsyncThunk(
  "course/getCourseDetail",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
      const response = await courseApi.getDetail(body);
      thunkAPI.dispatch(hideLoading());
      return response;
    } catch (err) {
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Không thể lấy được thông tin các khóa học"));
    }
  }
);

export const saveCourse = createAsyncThunk(
  "course/saveCourse",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
      const response = await courseApi.save(body.courseInfo);

      let courseContentBody = {
        courseId: +response.data,
        ...body.courseContentInfo,
      };
      await courseApi.saveCourseContent(courseContentBody);
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showSuccess("Lưu thông tin khóa học thành công"));
      thunkAPI.dispatch(getDetail({ id: +response.data }));

      window.location.assign(
        PAGE_URL.COURSES.DETAIL.replace(":id", `${response.data}`)
      );
    } catch (err) {
      console.log(err);
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Không thể lưu được thông tin  khóa học"));
    }
  }
);

export const editCourse = createAsyncThunk(
  "course/editCourse",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
      const response = await courseApi.update(body.courseInfo);
      let courseContentBody = {
        courseId: +response.data,
        ...body.courseContentInfo,
      };

      let courseContentDeleteDto = {
        deleteCourseContent: body.deleteCourseContent,
        deleteVideo: body.deleteVideo,
      };

      await courseApi.updateContent({
        courseContentBody: courseContentBody,
        courseContentDeleteDto: courseContentDeleteDto,
      });
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(getDetail({ id: +response.data }));
      thunkAPI.dispatch(showSuccess("Thay đổi thông tin khóa học thành công"));
    } catch (err) {
      console.log(err);
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Không thể lưu được thông tin  khóa học"));
    }
  }
);

export const fetchCourseByCondition = createAsyncThunk(
  "course/fetchCourseByCondition",
  async (body, thunkAPI) => {
    thunkAPI.dispatch(showLoading());
    try {
      const response = await courseApi.getByCondition(body);
      thunkAPI.dispatch(hideLoading());
      return response;
    } catch (err) {
      thunkAPI.dispatch(hideLoading());
      thunkAPI.dispatch(showError("Không thể lấy được thông tin các khóa học"));
    }
  }
);

export const courseSlice = createSlice({
  name: "course",
  initialState,
  reducers: {
    clearState: (state, action) => {
      return initialState;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchCourseByCondition.fulfilled, (state, action) => {
        // Add user to the state array
        state.courses = action.payload.content;
        state.total = action.payload.total;
      })
      .addCase(getDetail.fulfilled, (state, action) => {
        state.courseDetail = action.payload;
        state.courseDetail.fileFromServer = {
          uid: "-1",
          name: `${state.courseDetail.image}`,
          status: "done",
          url: `${state.courseDetail.image}`,
          thumbUrl: `${state.courseDetail.image}`,
        };
      });
  },
});

export const { getAll, clearState } = courseSlice.actions;

export default courseSlice.reducer;
