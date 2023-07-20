import Head from 'next/head';
import { withAuthenticationRequired } from '@auth0/auth0-react';
import { useAuth0 } from '@auth0/auth0-react';
import Layout from '../../components/layout/Layout';
import { Loading } from '../../components/Loading';

function UserInfo({ }) {
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

    if (isLoading) {
        return <Loading />;
    }

    return (
        <Layout>
        <Head>
            <title>User Dashboard</title>
        </Head>
            <h1>Hello "{user.name}"!</h1>
            <p>
                Your user info: {JSON.stringify(user)}
            </p>
        </Layout>
    );
}

export default withAuthenticationRequired(UserInfo);