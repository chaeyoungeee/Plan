export const INIT = "USER/INIT"
export const initUser = user => ({type:INIT, user})

const initialState = {
    userId: null,
    nickname: null,
    plans: null,
    categories: null
}


const user = (state = initialState, action) => {
    switch(action.type) {
        case INIT:
            return {
                ...state,
                userId: action.user.userId,
                nickname: action.user.nickname,
                plans: action.user.plans,
                categories: action.user.categories,
            };

        default:
            return state
    }
}

export default user;