import { applyMiddleware, combineReducers, compose, createStore } from "redux";
import reduxThunk from "redux-thunk";
import { AuthReducer } from "./reducers/AuthReducer";
import { ResultReducer } from "./reducers/ResultReducer";
import { QuestionReducer } from "./reducers/QuestionReducer";
import { Quizreducer } from "./reducers/Quizreducer";
import { CourseReducer } from "./reducers/CourseReducer";
const rootReducer = combineReducers({
  AuthReducer,
  QuestionReducer,
  ResultReducer,
  Quizreducer,
  CourseReducer,
});

let middleWare = applyMiddleware(reduxThunk);
let composeCustom = compose(middleWare);

export const store = createStore(rootReducer, composeCustom);
