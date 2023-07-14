import Head from 'next/head';
import Layout from '../../../components/layout/Layout';
import axios from "axios";
import useSWR from 'swr';

export default function Countries() {
    console.log("Rendering /swr/countries");
    
    const address = `https://restcountries.com/v3.1/all`;
    const fetcher = async (url) => await axios.get(url).then((res) => res.data);
    const { data, error } = useSWR(address, fetcher);
    
    if (error) return <div>failed to load</div>
    if (!data) return <div>loading...</div>
    
    return (
        <Layout>
        <Head>
            <title>All Countries</title>
        </Head>
        <h1>All Countries</h1>
        <ul>
          {data.map(({ name: { official } }) => (
            <li key={official}>
              {official}
            </li>
          ))}
        </ul>
        </Layout>
    );
}