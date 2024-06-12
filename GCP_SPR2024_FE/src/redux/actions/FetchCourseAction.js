// actions/courseActions.js
import axios from "axios";
import { BASE_URL } from "../../data/const";
import {
  FETCH_COURSE_DETAIL_FAILURE,
  FETCH_COURSE_DETAIL_SUCCESS,
} from "../../data/courseConst";

export const fetchCourseDetailSuccess = (courseDetail) => ({
  type: FETCH_COURSE_DETAIL_SUCCESS,
  payload: courseDetail,
});

export const fetchCourseDetailFailure = (error) => ({
  type: FETCH_COURSE_DETAIL_FAILURE,
  payload: error,
});

export const fetchCourseDetail = (courseId) => async (dispatch) => {
  try {
    const response = await axios.get(BASE_URL + `api/v1/courses/${courseId}`);
    dispatch(fetchCourseDetailSuccess(response.data));
  } catch (error) {
    console.error("Error fetching course detail:", error);
    dispatch(fetchCourseDetailFailure(error.message));
  }
};
