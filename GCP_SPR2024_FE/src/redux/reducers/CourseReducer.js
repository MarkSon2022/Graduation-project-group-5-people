// reducers/courseReducer.js
import {
  FETCH_COURSE_DETAIL_SUCCESS,
  FETCH_COURSE_DETAIL_FAILURE,
} from "../../data/courseConst";

const initialState = {
  courseDetail: null,
  error: null,
};

export const CourseReducer = (state = initialState, action) => {
  switch (action.type) {
    case FETCH_COURSE_DETAIL_SUCCESS:
      return {
        ...state,
        courseDetail: action.payload,
        error: null,
      };
    case FETCH_COURSE_DETAIL_FAILURE:
      return {
        ...state,
        error: action.payload,
      };
    default:
      return state;
  }
};
