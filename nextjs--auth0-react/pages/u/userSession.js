import Head from 'next/head';
import { withAuthenticationRequired } from '@auth0/auth0-react';
import Layout from '../../components/layout/Layout';
import { Loading } from '../../components/Loading';
import { ErrorComponent } from '../../components/Error';

import { useApi } from '../../hooks/use-api';

function UserSessionPage({ }) {
    const {
        loading,
        error,
        data,
      } = useApi(process.env.NEXT_PUBLIC_SPRING_API_URL + '/api/v1/user/session', {
        audience: process.env.NEXT_PUBLIC_AUDIENCE,
        scope: process.env.NEXT_PUBLIC_SCOPE,
      });
    
      if (loading) {
        return <Loading />;
      }
    
      if (error) {
        return <ErrorComponent message={error.message} />;
      }
    
      return (
        <Layout>
        <Head>
            <title>Spring Demo API</title>
        </Head>
            <p>
              /api/v1/user/session: {JSON.stringify(data)}
            </p>
        </Layout>
    );
}

export default withAuthenticationRequired(UserSessionPage);