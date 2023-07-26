import Head from 'next/head';
import { useEffect, useState } from 'react';
import { withAuthenticationRequired } from '@auth0/auth0-react';
import { useAuth0 } from '@auth0/auth0-react';
import Layout from '@/components/layout';
import { Loading } from '@/components/Loading';

const authorizationParams = {
    audience: process.env.NEXT_PUBLIC_AUDIENCE,
    scope: process.env.NEXT_PUBLIC_SCOPE,
};

function UserTokenPage({ }) {
    // Returns Auth0ContextInterface<TUser>
    const {
        // Auth state:
        error,
        isAuthenticated,
        isLoading,
        user,
        // Auth methods:
        getAccessTokenSilently,
        getAccessTokenWithPopup,
        getIdTokenClaims,
        loginWithRedirect,
        loginWithPopup,
        logout,
      } = useAuth0();

    const [state, setState] = useState({
        error: null,
        loading: true,
        accessToken: null,
    });

    useEffect(() => {
        (async () => {
          try {
            const accessToken = await getAccessTokenSilently({ authorizationParams });
            setState({
                accessToken,
                loading: false,
            });
          } catch (error) {
            setState({
              error,
              loading: false,
            });
          }
        })();
      }, []);

    if (isLoading || state.loading) {
        return <Loading />;
    }

    return (
        <Layout>
        <Head>
            <title>User Dashboard</title>
        </Head>
            <h1>Hello "{user.name}"!</h1>
            {state.error && (
            <p>
                Error: {JSON.stringify(state.error)}
            </p>
            )}
            {state.accessToken && (
            <p>
                Your Access Token: {JSON.stringify(state.accessToken)}
            </p>
            )}
        </Layout>
    );
}

export default withAuthenticationRequired(UserTokenPage);