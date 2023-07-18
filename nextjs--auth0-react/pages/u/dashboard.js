import Head from 'next/head';
import { withAuthenticationRequired } from '@auth0/auth0-react';
import { useAuth0 } from '@auth0/auth0-react';
import Layout from '../../components/layout/Layout';
import { Loading } from '../../components/Loading';

function Dashboard({ }) {
    console.log("Rendering /u/dashboard");
    const { isAuthenticated, isLoading, user, loginWithRedirect, logout } =
    useAuth0();

    if (isLoading) {
        return <Loading />;
    }

    return (
        <Layout>
        <Head>
            <title>User Dashboard</title>
        </Head>
            <h1>User Dashboard for "{user.name}"</h1>
        </Layout>
    );
}

export default withAuthenticationRequired(Dashboard);