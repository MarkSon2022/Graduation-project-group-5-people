import "./App.css";
import { Router, Switch, Redirect } from "react-router-dom";
import { createBrowserHistory } from "history";
import Home from "./pages/Home/Home";
import { UserTemplate } from "./template/UserTemplate/UserTemplate";
import Login from "./pages/Login/Login";
import Course from "./pages/Course/Course";
import CourseDetails from "./components/Course/CourseDetails";
import { AdminTemplate } from "./template/AdminTemplate/AdminTemplate";
import AdminPage from "./pages/AdminPage/AdminPage";
import AdminTableManager from "./components/AdminTable/AdminTableManager";
import SubScription from "./pages/Subcription/SubScription";
import ConfirmPage from "./pages/Subcription/ConfirmSubscription";
import LessonDetail from "./pages/Course/LessonDetail";
import { Route } from "react-router-dom/cjs/react-router-dom.min";

import Context from "./template/UserTemplate/Context";
import Certificate from "./pages/Certificate/Certificate";

export const history = createBrowserHistory();
function App() {
  return (
    <Router history={history}>
      <Switch>
        <AdminTemplate path="/admin-page" exact Component={AdminPage} />
        <AdminTemplate
          path="/admin-table-manager"
          exact
          Component={AdminTableManager}
        />

        <UserTemplate path="/sign-in" exact Component={Login} />
        <UserTemplate path="/course" exact Component={Course} />
        <Route path="/" exact>
          <Redirect to="/course" />
        </Route>
        <UserTemplate path="/course-detail" exact Component={CourseDetails} />
        <UserTemplate path="/subscription" exact Component={SubScription} />
        <UserTemplate
          path="/confirm-subscription"
          exact
          Component={ConfirmPage}
        />
        <Route path="/lesson-detail" exact component={LessonDetail} />
        <Route path="/certificate" exact component={Certificate} />


        <UserTemplate path="/" exact Component={Home} />
      </Switch>
    </Router>
  );
}

export default App;
