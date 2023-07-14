import Head from 'next/head';
import Layout from '../components/layout/Layout';
import { useUser } from '@auth0/nextjs-auth0/client';

function Welcome() {
  const { user, error, isLoading } = useUser();

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>{error.message}</div>;

  if (user) {
    return (
      <div>
        Welcome {user.name}! <a href="/api/auth/logout">Logout Here</a>
      </div>
    );
  }

  return (
    <div>
      Welcome! <a href="/api/auth/login">Login Here</a>
    </div>
  );
};

export default function () {
    return (
        <Layout>
        <Head>
            <title>Welcome Page</title>
        </Head>
        <h1>Welcome Page</h1>
        { Welcome() }
        </Layout>
    );
  };