export const INIT = "USER/INIT"
export const DELETE_CATEGORY = 'USER/DELETE_CATEGORY';
export const ADD_PLAN = 'USER/ADD_PLAN';
export const ADD_CATEGORY = 'USER/ADD_CATEGORY'
export const initUser = user => ({type:INIT, user})
export const deleteCategory = categoryId => ({ type: DELETE_CATEGORY, categoryId });
export const addPlan = plan => ({ type: ADD_PLAN, plan})
export const addCategory = category => ({ type: ADD_CATEGORY, category})

const initialState = {
    userId: null,
    nickname: null,
    plans: null,
    friends: null,
    categories: null
}


const user = (state = initialState, action) => {
    switch (action.type) {
        case INIT:
            return {
                ...state,
                userId: action.user.userId,
                nickname: action.user.nickname,
                plans: action.user.plans,
                categories: action.user.categories,
                friends: action.user.friends
            };

        case DELETE_CATEGORY:
            const updatePlans = state.plans.filter((plan) => plan.categoryId !== action.categoryId);
            const updateCategories = state.categories.filter((category) => category.categoryId !== action.categoryId);

            return {
                ...state,
                plans: updatePlans,
                categories: updateCategories,
            };

        case ADD_PLAN:
            return {
                ...state,
                plans: [...state.plans, action.plan],
            };

        case ADD_CATEGORY:
            return {
                ...state,
                categories: [...state.categories, action.category],
            };

        default:
            return state;
    }
}

export default user;