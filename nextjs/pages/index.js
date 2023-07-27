import Head from 'next/head'
import Image from 'next/image';
import styles from '../styles/Index.module.scss';
import Layout from '@/components/layout';
import Card from '@/components/card';

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
      <p>
        When a route is loaded with Next.js, the initial HTML is rendered on the server.
        This HTML is then progressively enhanced in the browser.
        Server Components allow developers to, for example, move data fetching to the server,
        closer to the database, or keep large dependencies that previously would impact the
        client JavaScript bundle size on the server, leading to improved performance.
        All components inside the App Router are Server Components by default, and developers can also
        optionally opt-in to Client Components using the 'use client' directive.
        Server and Client Components can be combined in the same component tree. To improve the
        application performance, the recommendation is to move Client Components to the leaves of the
        component tree where possible.
      </p>

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
                In production it will run at build runtime.
              </li>
            </ul>
          </Card>

          <Card title="Dynamic SSR (Server Side Rendering)" href="/ssr/countries"
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
              If you need to fetch data at request time instead of at build time, you can try Server-side Rendering.
              To do this, you need to export getServerSideProps instead of getStaticProps from your page.
              Because getServerSideProps is called at request time, its parameter (context) contains
              request specific parameters.
            </p>
          </Card>
        </div>

        <hr/>

        <h1>More Dynamic Rendering</h1>

        <Card title="Countries API with SWR Client Hook (Reload every time)" href="/swr/countries"></Card>

        <Card title="Countries API with SWR Client Hook with Reducer Context" href="/withContext/countries"></Card>

        <div className={styles.grid}>
          <Card title="Dynamic Counter using Client Side Rendering" href="/challenges/counter"></Card>
        </div>
      </main>
    </Layout>
    
  )
}