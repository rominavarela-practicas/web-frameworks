import { useEffect, useState } from 'react';
import { useAuth0 } from '@auth0/auth0-react';

/*
const {
    loading,
    error,
    data: users = [],
  } = useApi(`http://localhost:${PORT}/users`, {
    audience: process.env.NEXT_PUBLIC_AUDIENCE,
    scope: 'profile email read:users',
  });
*/

export const useApi = (url, options) => {
  const { getAccessTokenSilently } = useAuth0();
  const [state, setState] = useState({
    error: null,
    loading: true,
    data: null,
  });

  useEffect(() => {
    (async () => {
      try {
        const { audience, scope, ...fetchOptions } = options;
        const accessToken = await getAccessTokenSilently({
          authorizationParams: { audience, scope },
        });
        const res = await fetch(url, {
          ...fetchOptions,
          headers: {
            ...fetchOptions.headers,
            // Add the Authorization header to the existing headers
            Authorization: `Bearer ${accessToken}`,
          },
        });
        setState({
          ...state,
          data: await res.json(),
          error: null,
          loading: false,
        });
      } catch (error) {
        setState({
          ...state,
          error,
          loading: false,
        });
      }
    })();
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  return state;
};