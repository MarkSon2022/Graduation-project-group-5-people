let ad = '';
if(localStorage.getItem('admin')){
    ad = localStorage.getItem('admin');
}
let dataAccount = '';
if(localStorage.getItem('account')){
    dataAccount = JSON.parse(localStorage.getItem('account'));
}
const stateDefault = {
    user:dataAccount,
    admin : ad,
    
}


export const AuthReducer = (state = stateDefault, action) => {
    switch (action.type) {
        case 'LOGIN': {
            state.user = action.user;
            return { ...state }
        }
        default: return state;
    }
}