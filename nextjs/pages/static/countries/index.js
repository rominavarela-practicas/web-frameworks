import Head from 'next/head';
import Layout from '../../../components/layout';
import { getCountriesData } from '../../../lib/countries';

export async function getStaticProps() {
    console.log("getStaticProps at /static/countries");
    const allCountriesData = await getCountriesData();
    return {
      props: {
        allCountriesData,
      },
    };
  }

export default function Countries({ allCountriesData }) {
    console.log("Rendering /static/countries");
    return (
        <Layout>
        <Head>
            <title>All Countries</title>
        </Head>
        <h1>All Countries</h1>
        <ul>
          {allCountriesData.map(({ name: { official } }) => (
            <li key={official}>
              {official}
            </li>
          ))}
        </ul>
        </Layout>
    );
}