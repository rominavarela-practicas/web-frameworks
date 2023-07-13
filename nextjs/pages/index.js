import Head from 'next/head'
import styles from '../styles/Index.module.scss';
import Card from '../components/card/Card.js';
import Link from 'next/link'

export default function Home() {
  console.log("Rendering Home");
  return (
    <div className={styles.container}>
      <Head>
        <title>Hello Next!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main>

      <h1>Countries API</h1>

        <div className={styles.grid}>
          <Card title="List of Countries with Static Site Rendering" href="/static/countries">
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

          <Card title="List of Countries with Server Side Rendering" href="/ssr/countries">
            <p>
              Because getServerSideProps is called at request time, its parameter (context) contains
              request specific parameters. You should use getServerSideProps only if you need to pre-render
              a page whose data must be fetched at request time.
            </p>
          </Card>

          <Card title="Dynamic Dashboard using Client Side Rendering" href="/swr/countries">
            <p>
              If you do not need to pre-render the data, you can also use the Client-side Rendering strategy:
            </p>
            <ul>
              <li>
                Statically generate (pre-render) parts of the page that do not require external data.
              </li>
              <li>
                When the page loads, fetch external data from the client using JavaScript and populate the remaining parts.
              </li>
            </ul>
          </Card>
        </div>
      </main>

      <footer className={styles.footer}>
        <Link
          href="https://github.com/rominavarela"
          target="_blank"
          rel="noopener noreferrer"
        >
          Follow me @Github
        </Link>
      </footer>
    </div>
  )
}