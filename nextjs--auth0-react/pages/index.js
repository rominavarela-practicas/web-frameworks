import Head from 'next/head'
import styles from '../styles/Index.module.scss';
import Layout from '@/components/layout';
import Card from '@/components/card';

export default function Home() {
  console.log("Rendering Home");
  return (
    <Layout>
      <Head>
        <title>Hello Next + Auth0-React!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main>

      <h1>Hello Next + Auth0-React!</h1>

      <hr />
        <div className={styles.grid}>
          <Card title="Your User Info" href="/u/userInfo">
            Use the useAuth0 hook in your components to access the auth state and methods.
          </Card>

          <Card title="Your Access Token" href="/u/accessToken"></Card>
        </div>
        <hr />

        <div className={styles.grid}>
          <Card title="User Session" href="/u/userSession">
            In this architecture, the SPA will pass the Access Token in the HTTP Authorization
            header when making calls to the API
          </Card>

          <Card title="Admin Session" href="/u/adminSession"></Card>
        </div>
      </main>
    </Layout>
    
  )
}