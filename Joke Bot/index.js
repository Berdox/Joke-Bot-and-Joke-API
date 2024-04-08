const { Client, GatewayIntentBits } = require('discord.js');
const axios = require('axios');
const client = new Client({ intents: [
                                      GatewayIntentBits.Guilds,
                                      GatewayIntentBits.MessageContent,
                                      GatewayIntentBits.GuildMessages, 
                                      GatewayIntentBits.GuildMessageReactions, 
                                      GatewayIntentBits.GuildMessageTyping, 
                                      GatewayIntentBits.GuildIntegrations, 
                                      GatewayIntentBits.DirectMessages
                                    ] });
const { prefix, token } = require('./config.json');

async function getJoke() {
    const res = await axios.get('http://localhost:8080/api/v1/random');
    return res.data.joke;
}


client.once('ready', () => {
    console.log('Bot is ready!');
});

client.on('messageCreate', async message => {
    // Ignore messages from the bot itself and messages that don't start with the prefix
    if (!message.content.startsWith(prefix) || message.author.bot) return;

    // Split the message content into command and arguments
    const args = message.content.slice(prefix.length).trim().split(/ +/);
    const command = args.shift().toLowerCase();

    // Check if the command is 'hello'
    if (command === 'joke') {
        // Reply with a simple message
        const joke = await getJoke();
        message.channel.send(joke);
    }
});

client.login(token);
