export const INIT = "USER/INIT"
export const RESET = "USER/RESET"
export const DELETE_CATEGORY = 'USER/DELETE_CATEGORY';
export const ADD_PLAN = 'USER/ADD_PLAN';
export const ADD_CATEGORY = 'USER/ADD_CATEGORY'
export const ADD_FRIEND = 'USER/ADD_FRIEND'
export const TOGGLE_STATUS = 'USER/TOGGLE_STATUS';
export const UPDATE_CATEGORY = 'USER/UPDATE_CATEGORY'
export const UPDATE_PLAN = 'USER/UPDATE_PLAN';

export const initUser = user => ({type:INIT, user})
export const deleteCategory = categoryId => ({ type: DELETE_CATEGORY, categoryId });
export const addPlan = plan => ({ type: ADD_PLAN, plan})
export const addCategory = category => ({ type: ADD_CATEGORY, category})
export const AddFriend = friend => ({ type: ADD_FRIEND , friend});
export const toggleStatus = planId => ({type: TOGGLE_STATUS, planId})
export const resetUser = () => ({type: RESET})
export const updateCategory = (category) => ({ type: UPDATE_CATEGORY, category });
export const updatePlan = (plan) => ({ type: UPDATE_PLAN, plan});

const initialState = {
    userId: null,
    nickname: null,
    plans: [],
    friends: [],
    categories: []
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
                friends: action.user.friends,
            };

        case RESET:
            return initialState;

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

        case ADD_FRIEND:
            return {
                ...state,
                friends: [...state.friends, action.friend],
            };

        case TOGGLE_STATUS:
            return {
                ...state,
                plans: state.plans.map((plan) =>
                    plan.planId === action.planId
                        ? { ...plan, status: plan.status === 'COMPLETED' ? 'INCOMPLETE' : 'COMPLETED' }
                        : plan
                ),
            };

        case UPDATE_CATEGORY:
            return {
                ...state,
                categories: state.categories.map((category) =>
                    category.categoryId === action.category.categoryId
                        ? { ...category, name: action.category.name, color: action.category.color }
                        : category
                ),
            };

        case UPDATE_PLAN:
            return {
                ...state,
                plans: state.plans.map((plan) =>
                    plan.planId === action.plan.planId
                        ? { ...plan, title: action.plan.title, start: action.plan.start, end: action.plan.end }
                        : plan
                ),
            };

        default:
            return state;
    }
}

export default user;