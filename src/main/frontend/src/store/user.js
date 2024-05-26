export const INIT = "USER/INIT"
export const DELETE_CATEGORY = 'USER/DELETE_PLAN';
export const initUser = user => ({type:INIT, user})
export const deleteCategory = categoryId => ({ type: DELETE_CATEGORY, categoryId });

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

        case DELETE_CATEGORY:
            let updatePlans = state.plans.filter((plan) => plan.category.categoryId !== action.categoryId)
            let updateCategories = state.categories.filter((category) => category.categoryId !== action.categoryId)

            return {
                ...state,
                plans: updatePlans,
                categories: updateCategories,
            };

        default:
            return state
    }
}

export default user;