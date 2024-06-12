import { http } from "../../api/config";

export const GetQuizAction = (id, history) => {
    return async (dispatch) => {
        try {
            let result = await http.get(`/quiz/getQuiz/${id}`);
           
            const action = {
                type: "GET_QUIZ_ID",
                quiz: result.data.questionRequestDTOS
            }
            
            dispatch(action)
            
            history.push('/quiz')
        } catch (error) {
            console.log(error);
        }
    }
}