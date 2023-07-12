import Head from 'next/head'
import styles from '../styles/Home.module.scss';

export default function Home() {
  console.log("Rendering Home");
  return (
    <div className={styles.container}>
      <Head>
        <title>Hello Next!</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main>
        <div className={styles.grid}>
          <a href="/static/countries" className={styles.card}>
            <h3>List of Countries with Static Site Rendering</h3>
            <p>
              getStaticProps only runs on the server-side. It won’t even be included in the JS bundle for the browser. That means you can write code such as direct database queries without them being sent to browsers.  
            </p>
            <ul>
              <li>
                In development it will run on every request
              </li>
              <li>
                In production it will run at runtime.
              </li>
            </ul>
          </a>
        </div>
      </main>

      <main>
        <div className={styles.grid}>
          <a href="/ssr/countries" className={styles.card}>
            <h3>List of Countries with Server Side Rendering</h3>
            <p>
              Because getServerSideProps is called at request time, its parameter (context) contains request specific parameters. You should use getServerSideProps only if you need to pre-render a page whose data must be fetched at request time.
            </p>
          </a>
        </div>
      </main>

      <main>
        <div className={styles.grid}>
          <a href="/dynamic/dashboard" className={styles.card}>
            <h3>Dynamic Dashboard using Client Side Rendering</h3>
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
          </a>
        </div>
      </main>

      <footer>
        <a
          href="https://vercel.com?utm_source=create-next-app&utm_medium=default-template&utm_campaign=create-next-app"
          target="_blank"
          rel="noopener noreferrer"
        >
          Powered by{' '}
          <img src="/vercel.svg" alt="Vercel" className={styles.logo} />
        </a>
      </footer>

      <style jsx>{`
        main {
          padding: 5rem 0;
          flex: 1;
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
        }
        footer {
          width: 100%;
          height: 100px;
          border-top: 1px solid #eaeaea;
          display: flex;
          justify-content: center;
          align-items: center;
        }
        footer img {
          margin-left: 0.5rem;
        }
        footer a {
          display: flex;
          justify-content: center;
          align-items: center;
          text-decoration: none;
          color: inherit;
        }
        code {
          background: #fafafa;
          border-radius: 5px;
          padding: 0.75rem;
          font-size: 1.1rem;
          font-family: Menlo, Monaco, Lucida Console, Liberation Mono,
            DejaVu Sans Mono, Bitstream Vera Sans Mono, Courier New, monospace;
        }
      `}</style>

      <style jsx global>{`
        html,
        body {
          padding: 0;
          margin: 0;
          font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto,
            Oxygen, Ubuntu, Cantarell, Fira Sans, Droid Sans, Helvetica Neue,
            sans-serif;
        }
        * {
          box-sizing: border-box;
        }
      `}</style>
    </div>
  )
}