const stateDefault = {
    quiz : [],
    
}


export const Quizreducer = (state = stateDefault, action) => {
    switch (action.type) {
        case 'GET_QUIZ_ID': {
            state.quiz = action.quiz;
            return { ...state }
        }
        default: return state;
    }
}