import Head from 'next/head';
import { withAuthenticationRequired } from '@auth0/auth0-react';
import Layout from '@/components/layout';
import { Loading } from '@/components/Loading';
import { ErrorComponent } from '@/components/Error';

import { useApi } from '@/hooks/useApi';

function Body({ }) {
  const {
      loading,
      error,
      data,
    } = useApi(process.env.NEXT_PUBLIC_SPRING_API_URL + '/api/v1/admin/session', {
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
    <p>
      /api/v1/admin/session: {JSON.stringify(data)}
    </p>
  );
}

function AdminSessionPage(props) {    
      return (
        <Layout>
        <Head>
            <title>Spring Demo API</title>
        </Head>
        { Body(props) }
        </Layout>
    );
}

export default withAuthenticationRequired(AdminSessionPage);