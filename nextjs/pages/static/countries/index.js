import Head from 'next/head';
import Layout from '../../../components/layout';
import { useCountries } from '../../../hooks/useCountries';

export async function getStaticProps() {
    console.log("getServerSideProps at /static/countries");
    const countries = await useCountries();
    return {
      props: {
        countries,
      },
    };
  }

export default function Countries({ countries }) {
    console.log("Rendering /ssr/countries");
    return (
        <Layout>
        <Head>
            <title>All Countries</title>
        </Head>
        <h1>All Countries</h1>
        <ul>
          {(countries.data || []).map(({ name: { official } }) => (
            <li key={official}>
              {official}
            </li>
          ))}
        </ul>
        </Layout>
    );
}