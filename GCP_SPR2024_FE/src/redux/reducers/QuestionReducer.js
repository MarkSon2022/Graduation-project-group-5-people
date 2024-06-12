const stateDefault = {
    queue : [],
    answers : [],
    trace : 0
}

export const QuestionReducer = (state = stateDefault, action) => {
    switch (action.type) {
        case 'QUIZ_QUESTION' : {
            state.queue = action.queue;
            return {...state}
        }
        default : return state
    }
}