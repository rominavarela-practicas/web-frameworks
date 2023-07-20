import Head from 'next/head'
import styles from '../styles/Index.module.scss';
import Layout from '../components/layout/Layout';
import Card from '../components/card/Card.js';

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
          <Card title="Your UserInfo" href="/u/userInfo">
            Use the useAuth0 hook in your components to access the auth state and methods.
          </Card>
        </div>
      </main>
    </Layout>
    
  )
}