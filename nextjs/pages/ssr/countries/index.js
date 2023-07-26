import Head from 'next/head';
import Layout from '../../../components/layout/Layout';
import { fetchCountries } from '../../../backend/fetch/fetchCountries';

export async function getServerSideProps() {
    console.log("getServerSideProps at /ssr/countries");
    const countries = await fetchCountries();
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