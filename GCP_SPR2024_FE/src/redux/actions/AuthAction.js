import { http } from "../../api/config";


export const LoginAction = (value, history) => {
  return async (dispatch) => {
    try {
      let result = await http.post("/login", value);
      console.log(result)
      if(result.data.account.role === 1){
        console.log("1")
        const action = {
          type : "LOGIN",
          user : result.data.account
        }
        localStorage.setItem('account',JSON.stringify(result.data.account))
        dispatch(action)
        history.push("/");
      }
    } catch (error) {
      console.log(error);
    }
  };
};
