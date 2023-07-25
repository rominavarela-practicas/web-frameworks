import Head from 'next/head'
import Image from 'next/image';
import styles from '../styles/Index.module.scss';
import Layout from '../components/layout/Layout';
import Card from '../components/card/Card.js';

export default function Home() {
  console.log("Rendering Home");
  return (
    <Layout>
      <Head>
        <title>Hello Next!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main>

      <h1>Hello Next!</h1>

      <hr />

      <h1>Countries API</h1>

        <div>
          <Card title="Static Site Rendering" href="/static/countries"
            image={(
              <Image
                src="/Nextjs-Static Rendering.drawio.png"
                width={600}
                height={400}
                alt="Nextjs-Static Rendering Diagram"
              />
            )}
          >
            <p>
              getStaticProps only runs on the server-side. It won’t even be included in
              the JS bundle for the browser. That means you can write code such as direct
              database queries without them being sent to browsers.  
            </p>
            <ul>
              <li>
                In development it will run on every request
              </li>
              <li>
                In production it will run at runtime.
              </li>
            </ul>
          </Card>

          <Card title="Dynamic Rendering" href="/ssr/countries"
            image={(
              <Image
                src="/Nextjs-Dynamic Rendering.drawio.png"
                width={600}
                height={400}
                alt="Nextjs-Static Rendering Diagram"
              />
            )}
          >
            <p>
              Because getServerSideProps is called at request time, its parameter (context) contains
              request specific parameters. You should use getServerSideProps only if you need to pre-render
              a page whose data must be fetched at request time.
            </p>
          </Card>
        </div>

        <hr/>

        <h1>More Dynamic Rendering</h1>

        <Card title="Countries API with SWR Client Hook" href="/swr/countries"></Card>

        <div className={styles.grid}>
          <Card title="Dynamic Counter using Client Side Rendering" href="/challenges/counter"></Card>
        </div>
      </main>
    </Layout>
    
  )
}