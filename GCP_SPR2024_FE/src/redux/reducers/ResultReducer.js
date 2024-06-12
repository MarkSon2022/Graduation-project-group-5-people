const stateDefault = {
    useId : null,
    result : [],
}

export const ResultReducer = (state = stateDefault, action) => {
    switch (action.type) {
        case 'QUIZ_RESULT' : {
            state.useId = action.useId;
            return {...state}
        }
        default : return state
    }
}