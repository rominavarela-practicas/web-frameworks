import * as actions from '@/context/reducer/actions';

export const initialState = {
    countries: {},
 };
 
export const reducer = (state, action) => {
    switch (action.type) {
       case actions.SET_COUNTRIES: {
          return {
             ...state,
             countries: action.value,
          };
       }
       default:
         throw new Error('Unknown action type');
    }
 };