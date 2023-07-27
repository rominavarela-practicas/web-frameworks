import { createContext, useContext, useReducer } from 'react';
import { reducer, initialState } from './reducer';

const AppContext = createContext();

export function ReducerContext({ children }) {
   const [ state, dispatch ] = useReducer(reducer, initialState);
   
   return (
      <AppContext.Provider value={{ state, dispatch }}>
         {children}
      </AppContext.Provider>
   );
}

export function useReducerContext() {
   return useContext(AppContext);
}