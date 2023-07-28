import { createContext, useContext, useReducer } from 'react';
import { reducer, initialState } from './reducer';

const ReducerContext = createContext();

export function ReducerProvider({ children }) {
   const [ state, dispatch ] = useReducer(reducer, initialState);
   
   return (
      <ReducerContext.Provider value={{ state, dispatch }}>
         {children}
      </ReducerContext.Provider>
   );
}

export function useReducerContext() {
   return useContext(ReducerContext);
}