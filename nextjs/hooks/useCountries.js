import useSWR from 'swr';
import axios from "axios";
import { useReducerContext } from "@/context/reducer/ReducerContext";
import * as actions from '@/context/reducer/actions';

export const useCountries = () => {
  const { state, dispatch } = useReducerContext();
  const { countries } = state;

  if (!state.countries.data) {
    const address = `https://restcountries.com/v3.1/all`;
    const fetcher = async (url) => await axios.get(url).then((res) => res.data);
    const { data, error } = useSWR(address, fetcher);
    dispatch({
      type: actions.SET_COUNTRIES,
      value: ({ data, error })
    });
  }

  return countries;
};